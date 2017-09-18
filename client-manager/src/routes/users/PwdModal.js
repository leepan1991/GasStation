import React, { PropTypes } from 'react'
import { Form, Input, InputNumber, Radio, Modal } from 'antd'
const FormItem = Form.Item
let confirmDirty = false;

const formItemLayout = {
  labelCol: {
    span: 6
  },
  wrapperCol: {
    span: 14
  },
}

const modal = ({
  visible,
  type,
  item = {},
  onOk,
  onCancel,
  form: {
    getFieldDecorator,
    validateFields,
    getFieldsValue,
    getFieldValue
  },
}) => {
  function handleOk () {
    validateFields((errors) => {
      if (errors) {
        return
      }
      const data = {
        ...getFieldsValue(),
        key: item.key
      }
      onOk(data)
    })
  }

  const modalOpts = {
    title: `${type === 'create' ? '新建用户' : '修改用户'}`,
    visible,
    onOk: handleOk,
    onCancel,
    wrapClassName: 'vertical-center-modal',
  }

  function checkConfirm (rule, value, callback) {
    if (value && confirmDirty) {
      validateFields(['confirm'], { force: true });
    }
    callback();
  }
  function checkPassword (rule, value, callback) {
    if (value && value !== getFieldValue('password')) {
      callback('两次输入的密码不一致');
    } else {
      callback();
    }
  }
  function handleConfirmBlur (e) {
    const value = e.target.value;
    confirmDirty = confirmDirty || !!value;
  }

  return (
    <Modal {...modalOpts}>
      <Form layout="horizontal">
        <FormItem label="密码：" hasFeedback {...formItemLayout}>
          {getFieldDecorator('password', {
            initialValue: item.password,
            rules: [{
              required: true,
              message: '密码未填写'
            }, {
              validator: checkConfirm
            }]
          })(<Input type="password" />)}
        </FormItem>
        <FormItem label="确认密码：" hasFeedback {...formItemLayout}>
          {getFieldDecorator('confirm', {
            rules: [{
              required: true,
              message: '确认密码'
            }, {
              validator: checkPassword
            }]
          })(<Input type="password" onBlur={handleConfirmBlur} />)}
        </FormItem>
      </Form>
    </Modal>
  )
}

modal.propTypes = {
  form: PropTypes.object.isRequired,
  visible: PropTypes.bool,
  type: PropTypes.string,
  item: PropTypes.object,
  onCancel: PropTypes.func,
  onOk: PropTypes.func
}

export default Form.create()(modal)
