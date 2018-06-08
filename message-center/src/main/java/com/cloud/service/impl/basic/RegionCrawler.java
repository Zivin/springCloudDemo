/**
 * @Project message-center
 * @Package com.cloud.service.impl.region
 * @FileName RegionCrawler.java
 */

package com.cloud.service.impl.basic;

import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cloud.base.constant.CommonConstant;
import com.cloud.base.util.BaseStringUtils;
import com.cloud.entity.basic.region.RegionVo;
import com.cloud.service.basic.RegionService;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * 区域爬虫
 * @Author zivin
 * @Date 2017年12月15日
 */
@Component
public class RegionCrawler extends BreadthCrawler implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(RegionCrawler.class);

    /**
     * URL_SEED 地址种子
     */
    private static final String URL_SEED = "http://www.xzqy.net/";

    /**
     * URL_REGEX URL正则
     */
    private static final String URL_REGEX = "http://www.xzqy.net/\\d{12}.htm";

    @Autowired
    private RegionService regionService;

    /**
     * @param crawlPath
     * @param autoParse
     */
    public RegionCrawler() {
        super("regionCrawler", true);
        // 起点地址
        this.addSeed(URL_SEED);
        // 开启20个线程进行爬取
        this.setThreads(30);
        this.getConf().setTopN(1000);
    }

    /*
     * (non-Javadoc)
     * @see
     * cn.edu.hfut.dmic.webcollector.fetcher.Visitor#visit(cn.edu.hfut.dmic.
     * webcollector.model.Page, cn.edu.hfut.dmic.webcollector.model.CrawlDatums)
     */
    @Override
    public void visit(Page page, CrawlDatums next) {
        if (URL_SEED.equals(page.url())) {
            // 种子地址，解析所有省份路径
            Elements provinces = page.select("div.navi").first().select("a");
            // 循环头部所有a链接
            for (Element element : provinces) {
                String href = element.absUrl("href");
                if (href.matches(URL_REGEX)) {
                    // 匹配正则的路径，加入遍历列表
                    next.add(new CrawlDatum(href));
                }
            }
        } else if (page.matchUrl(URL_REGEX)) {
            // 页面地址解析下级结构路径
            String parentCode = page.select("div.place a").last().attr("href").replace("./", "").replace(".htm", "");
            String regionName = page.select("h1").first().text();
            String regionCode = page.select("strong").first().text();

            // 查询父节点
            RegionVo parent = regionService.getByCode(parentCode);
            // 查询当前节点是否存在
            RegionVo regionVo = regionService.getByCode(regionCode);
            if (regionVo == null) {
                // 节点不存在，新增节点
                regionVo = new RegionVo();
                regionVo.setId(BaseStringUtils.getUUID());
                regionVo.setRegionName(regionName);
                regionVo.setRegionCode(regionCode);
                regionVo.setRegionLevel(getRegionLevel(regionCode));
                regionVo.setCreateUserId(CommonConstant.SUPER_ID);
                regionVo.setCreateTime(new Date());
                if (parent != null) {
                    regionVo.setParentId(parent.getId());
                } else {
                    regionVo.setParentId(CommonConstant.SUPER_ID);
                }
                regionService.save(regionVo);
            } else {
                // 节点存在，更新节点
                regionVo.setRegionName(regionName);
                regionVo.setRegionLevel(getRegionLevel(regionCode));
                regionVo.setUpdateUserId(CommonConstant.SUPER_ID);
                regionVo.setUpdateTime(new Date());
                if (parent != null) {
                    regionVo.setParentId(parent.getId());
                } else {
                    regionVo.setParentId(CommonConstant.SUPER_ID);
                }
                regionService.update(regionVo);
            }

            // 获取下级节点，包括直属下级和下下级，因为部分数据会少一级，在列表上不一定是直属关系，需要进行兼容，后续自动过滤重复路径
            Elements children = page.select("td>a");
            if (CollectionUtils.isEmpty(children)) {
                // children为空说明已经是最后一级，不需要继续往下遍历
                return;
            }

            // 遍历下级节点
            for (Element element : children) {
                String href = element.absUrl("href");
                // 不获取第五级村级数据，直接过滤
                if (href.endsWith("000.htm")) {
                    // 将下级节点加入遍历列表
                    next.add(new CrawlDatum(href));
                }
            }
        }
    }

    /**
     * 根据区域编码计算区域等级
     * @param regionCode
     * @return
     */
    public static int getRegionLevel(String regionCode) {
        if (StringUtils.isBlank(regionCode)) {
            return 0;
        }

        // 获取编码中最后一个不是0的位置
        int lastNum = -2;
        for (int i = regionCode.length() - 1; i >= 0; i--) {
            if (regionCode.charAt(i) != '0') {
                lastNum = i;
                break;
            }
        }

        // 计算等级
        int level = lastNum / 2 + 1;
        // 前3级每一级2位，可以直接判断等级
        if (level < 4) {
            return level;
        }
        // 后2级每一级3位，需要再次计算
        level = lastNum / 3 + 2;
        return level;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        // 深度为10，最多读取10层路径
        try {
            this.start(10);
        } catch (Exception e) {
            logger.error("启动区域爬虫失败：", e);
        }
    }

}
