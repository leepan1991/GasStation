import React, {PropTypes} from 'react'
import {Form, Input, Select, Modal} from 'antd'
const FormItem = Form.Item
const Option = Select.Option

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
                 orgList = [],
                 item = {},
                 onOk,
                 onCancel,
                 form: {
                   getFieldDecorator,
                   validateFields,
                   getFieldsValue,
                 },
               }) => {
  function handleOk() {
    validateFields((errors) => {
      if (errors) {
        return
      }
      const data = {
        ...getFieldsValue(),
        key: item.key,
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

  return (
    <Modal {...modalOpts}>
      <Form layout="horizontal">
        <FormItem label="用户名" hasFeedback {...formItemLayout}>
          {getFieldDecorator('username', {
            initialValue: item.username,
            rules: [{
              required: true,
              message: '用户名未填写'
            }]
          })(<Input />)}
        </FormItem>
        {type === 'create' && <FormItem label="密码" hasFeedback {...formItemLayout}>
          {getFieldDecorator('password', {
            initialValue: item.password,
            rules: [{
              required: true,
              message: '密码未填写'
            }]
          })(<Input type="password"/>)}
        </FormItem>}
        <FormItem label="邮箱" hasFeedback {...formItemLayout}>
          {getFieldDecorator('email', {
            initialValue: item.email,
            rules: [{
              required: true,
              message: '邮箱未填写'
            }]
          })(<Input />)}
        </FormItem>
        <FormItem label="电话" hasFeedback {...formItemLayout}>
          {getFieldDecorator('phone', {
            initialValue: item.phone,
            rules: [{
              required: true,
              message: '电话不能为空'
            }]
          })(<Input />)}
        </FormItem>
        <FormItem label="所属机构" hasFeedback {...formItemLayout}>
          {getFieldDecorator('orgId', {
            initialValue: item.orgId ? item.orgId + '' : undefined,
            rules: [{
              required: true,
              message: '所属机构未选择'
            }]
          })(<Select>
            {
              orgList.map(item => {
                return <Option value={item.id + ''} key={item.id + ''}>{item.name}</Option>
              })
            }
          </Select>)}
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
