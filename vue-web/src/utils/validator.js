const rules = {
  required: () => {
    return {required: true, message: "必填项不能为空", trigger: "blur,change"};
  },
  email: () => {
    return {type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur'};
  },
  number: () => {
    return {
      validator: (rule, value, callback) => {
        if (value !== null && value !== '') {
          let reg = /^[0-9]*[1-9][0-9]*$/;
          if (!reg.test(value)) {
            return callback(new Error('请输入正确的数字格式'));
          }
        }
        return callback();
      }, trigger: 'blur'
    };
  },
  min: (params) => {
    let length = parseInt(params[0]);
    return {min: length, message: '不能输入少于' + length + '个字符', trigger: 'blur'};
  },
  max: (params) => {
    let length = parseInt(params[0]);
    return {max: length, message: '不能输入多于' + length + '个字符', trigger: 'blur'};
  },
  between: (params) => {
    let min = parseInt(params[0]);
    let max = parseInt(params[1]);
    return {min: min, max: max, message: '请输入' + min + '-' + max + '个字符', trigger: 'blur'};
  },
  length: (params) => {
    let length = parseInt(params[0]);
    return {len: length, message: '请输入' + length + '个字符', trigger: 'blur'};
  },
  phone: () => {
    return {
      validator: (rule, value, callback) => {
        if (value !== null && value !== '') {
          let reg = /^1\d{10}$/;
          if (!reg.test(value)) {
            return callback(new Error('请输入正确的联系电话'));
          }
        }
        return callback();
      }, trigger: 'blur'
    };
  },
  password: () => {
    return {
      validator: (rule, value, callback) => {
        let reg = /^[A-Za-z0-9]{6,20}$/;
        if (!reg.test(value)) {
          return callback(new Error('请输入6-20位字母数字组合'));
        }
        return callback();
      }, trigger: 'blur'
    };
  }
}

const validator = {
  validate: (validator) => {
    let result = [];
    // 循环标记的所有规则
    validator.split('|').forEach((valid, i) => {
      let param = valid.split(':');
      // 获取校验规则
      if (rules[param[0]] != null) {
        if (param.length > 1) {
          // 带参数检验
          result.push(rules[param[0]](param[1].split(',')));
        } else {
          // 不带参数校验
          result.push(rules[param[0]]());
        }
      }
    });
    return result;
  }
}

export default validator
