import {getToken} from 'api/auth'

// 集成系统路径前缀
export const INTEGRATE_SYSTEM = '/integrateSystem';
// 用户中心路径前缀
export const USER_CENTER = '/userCenter';
// 信息中心路径前缀
export const MESSAGE_CENTER = '/messageCenter';

// 文件上传URL
export const FILE_UPLOAD_URL = process.env.BASE_API + '/messageCenter/fileUpload/upload';
// 文件上传header
export const FILE_UPLOAD_HEADER = {'X-Token': getToken()};
// 删除旧文件的参数名
export const FILE_UPLOAD_PARAM = 'oldFilePath';
