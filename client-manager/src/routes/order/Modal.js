import React, {PropTypes} from 'react'
import {Form, Input, InputNumber, Modal} from 'antd'
const FormItem = Form.Item

const formItemLayout = {
  labelCol: {
    span: 6
  },
  wrapperCol: {
    span: 16
  }
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
                 }
               }) => {
  function handleOk() {
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
    title: `${type === 'create' ? '新建' : '修改'}`,
    visible,
    onOk: handleOk,
    onCancel,
    wrapClassName: 'vertical-center-modal'
  }

  return (
    <Modal {...modalOpts}>
      <Form layout="horizontal">
        <FormItem label="客户单位" hasFeedback {...formItemLayout}>
          {getFieldDecorator('unitName', {
            initialValue: item.unitName,
            rules: [{
              required: true,
              message: '客户单位未填写'
            }]
          })(<Input />)}
        </FormItem>
        <FormItem label="联系地址" hasFeedback {...formItemLayout}>
          {getFieldDecorator('unitAddress', {
            initialValue: item.unitAddress,
            rules: [{
              required: true,
              message: '联系地址未填写'
            }]
          })(<Input />)}
        </FormItem>
        <FormItem label="联系电话" hasFeedback {...formItemLayout}>
          {getFieldDecorator('linkPhone', {
            initialValue: item.linkPhone,
            rules: [{
              required: true,
              message: '联系电话未填写'
            }]
          })(<Input />)}
        </FormItem>
        <FormItem label="联系人" hasFeedback {...formItemLayout}>
          {getFieldDecorator('linkUser', {
            initialValue: item.linkUser,
            rules: [{
              required: true,
              message: '联系人未填写'
            }]
          })(<Input />)}
        </FormItem>
        <FormItem label="气瓶数量" hasFeedback {...formItemLayout}>
          {getFieldDecorator('bottleNum', {
            initialValue: item.bottleNum,
            rules: [{
              required: true,
              message: '气瓶数量未填写'
            }]
          })(<InputNumber />)}
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
