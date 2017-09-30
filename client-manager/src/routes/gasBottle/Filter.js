import React, {PropTypes} from 'react'
import {Form, Button, Row, Col, Select, Input} from 'antd'
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

const Filter = ({
  location,
  onSearch,
  onExport,
  form: {
    getFieldDecorator,
    getFieldsValue
  }
}) => {
  let item = location.query
  let nums = []
  if (item.startNum || item.endNum) {
    nums = [item.startNum, item.endNum]
  }

  const getData = () => {
    const data = getFieldsValue()
    if (data.nums && data.nums.length > 0) {
      data.startNum = data.nums[0]
      data.endNum = data.nums[1]
    }
    data.nums = undefined
    return data
  }

  const searchHandle = () => {
    onSearch(getData())
  }

  const exportHandle = () => {
    onExport(getData())
  }

  return (
    <Form>
      <Row gutter={24}>
        <Col span="8">
          <FormItem label="气瓶登记码：" {...formItemLayout}>
            {getFieldDecorator('gasBottleRegCode', {
              initialValue: item.gasBottleRegCode,
              rules: [{
                required: false
              }]
            })(<Input />)}
          </FormItem>
        </Col>
        <Col span="8">
          <FormItem label="气瓶标识码：" {...formItemLayout}>
            {getFieldDecorator('gasBottleId', {
              initialValue: item.gasBottleId,
              rules: [{
                required: false
              }]
            })(<Input />)}
          </FormItem>
        </Col>
        <Col span="8">
          <FormItem label="瓶身码：" {...formItemLayout}>
            {getFieldDecorator('gasBottleCode', {
              initialValue: item.gasBottleCode,
              rules: [{
                required: false
              }]
            })(<Input />)}
          </FormItem>
        </Col>
      </Row>
      <Row gutter={24}>
        <Col span="8">
          <FormItem label="充装介质：" {...formItemLayout}>
            {getFieldDecorator('medium', {
              initialValue: item.medium,
              rules: [{
                required: false
              }]
            })(<Select allowClear>
              <Option value="0">工业氧气</Option>
              <Option value="1">工业氮气</Option>
              <Option value="2">工业氩气</Option>
              <Option value="3">液体二氧化碳</Option>
              <Option value="4">液体氧</Option>
              <Option value="5">液体氮</Option>
              <Option value="6">液体氩</Option>
              <Option value="7">液化石油气</Option>
              <Option value="8">丙烷气</Option>
              <Option value="9">乙炔气</Option>
              <Option value="10">混合气</Option>
            </Select>)}
          </FormItem>
        </Col>
        <Col span="8">
          <Button size="large" type="ghost" onClick={searchHandle}>查询</Button>
          <Button style={{ marginLeft: 20}} size="large" type="ghost" onClick={exportHandle}>导出</Button>
        </Col>
      </Row>
    </Form>
  )
}

Filter.propTypes = {
  form: PropTypes.object.isRequired,
  onSearch: PropTypes.func,
  onExport: PropTypes.func,
  location: PropTypes.object
}

export default Form.create()(Filter)
