import React, { PropTypes } from 'react'
import { Form, Input, Radio, Modal, TreeSelect } from 'antd'
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
   treeData = [],
   onOk,
   onCancel,
   form: {
     getFieldDecorator,
     validateFields,
     getFieldsValue,
   }
 }) => {
  const loop = (data) => {
    let list = [];
    data.forEach(function(item) {
      let data = {
        key: item.id + '',
        label: item.name,
        value: item.id + ''
      };
      if (item.children && item.children.length > 0) {
        data.children = loop(item.children);
      }
      list.push(data);
    });
    return list;
  }
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
    title: `${type === 'create' ? '新建' : '修改'}`,
    visible,
    onOk: handleOk,
    onCancel,
    wrapClassName: 'vertical-center-modal',
  }

  return (
    <Modal {...modalOpts}>
      <Form layout="horizontal">
        <FormItem label="名称" hasFeedback {...formItemLayout}>
          {getFieldDecorator('name', {
            initialValue: item.name,
            rules: [
              {
                required: true,
                message: '名称未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label="父级" hasFeedback {...formItemLayout}>
          {getFieldDecorator('parentId', {
            initialValue: (item.parentId || '') + '',
            rules: [
              {
                required: false,
                message: '父级未选择'
              }
            ]
          })(<TreeSelect treeData={loop(treeData)} />)}
        </FormItem>
        <FormItem label="编码" hasFeedback {...formItemLayout}>
          {getFieldDecorator('code', {
            initialValue: item.code,
            rules: [
              {
                required: true,
                message: '编码未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label="图标" hasFeedback {...formItemLayout}>
          {getFieldDecorator('icon', {
            initialValue: item.icon,
            rules: [
              {
                required: false,
                message: '图标未填写'
              }
            ]
          })(<Input />)}
        </FormItem>
        <FormItem label="状态" hasFeedback {...formItemLayout}>
          {getFieldDecorator('status', {
            initialValue: (item.status || 0) + '',
            rules: [
              {
                required: true,
                message: '状态未选择'
              }
            ]
          })(
            <Radio.Group>
              <Radio value="0">启用</Radio>
              <Radio value="1">禁用</Radio>
            </Radio.Group>
          )}
        </FormItem>
        <FormItem label="类型" hasFeedback {...formItemLayout}>
          {getFieldDecorator('type', {
            initialValue: (item.type || 0) + '',
            rules: [
              {
                required: true,
                message: '未选择'
              }
            ]
          })(
            <Radio.Group>
              <Radio value="0">菜单</Radio>
              <Radio value="1">权限</Radio>
            </Radio.Group>
          )}
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
