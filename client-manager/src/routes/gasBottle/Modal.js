import React, {PropTypes} from 'react'
import {Form, DatePicker, Modal} from 'antd'
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
      const data = getFieldsValue()
      data.ids = [item.id]
      data.nextCheckDate = data.nextCheckDate.format('YYYY-MM-DD')
      onOk(data)
    })
  }

  const modalOpts = {
    title: `编辑`,
    visible,
    onOk: handleOk,
    onCancel,
    wrapClassName: 'vertical-center-modal',
  }

  return (
    <Modal {...modalOpts}>
      <Form layout="horizontal">
        <FormItem label="下次检验时间" hasFeedback {...formItemLayout}>
          {getFieldDecorator('nextCheckDate', {
            initialValue: item.nextCheckDate,
            rules: [{
              required: true,
              message: '下次检验时间未填写'
            }]
          })(<DatePicker />)}
        </FormItem>
      </Form>
    </Modal>
  )
}

modal.propTypes = {
  form: PropTypes.object.isRequired,
  visible: PropTypes.bool,
  item: PropTypes.object,
  onCancel: PropTypes.func,
  onOk: PropTypes.func
}

export default Form.create()(modal)
