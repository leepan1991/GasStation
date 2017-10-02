import React, {PropTypes} from 'react'
import moment from 'moment'
import {Form, Button, Row, Col, Select, Input, DatePicker} from 'antd'
import {Search} from '../../components'
const FormItem = Form.Item
const Option = Select.Option
const {RangePicker} = DatePicker

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
  let fillingTime = []
  if (item.startFillingTime && item.endFillingTime) {
    fillingTime = [moment(item.startFillingTime, 'YYYY-MM-DD'), moment(item.endFillingTime, 'YYYY-MM-DD')]
  }

  const getData = () => {
    const data = getFieldsValue()
    if (data.fillingTime && data.fillingTime.length > 0) {
      data.startFillingTime = data.fillingTime[0].format('YYYY-MM-DD 00:00:00')
      data.endFillingTime = data.fillingTime[1].format('YYYY-MM-DD 23::59:59')
    }
    data.fillingTime = undefined
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
          <FormItem label="充装时间：" {...formItemLayout}>
            {getFieldDecorator('fillingTime', {
              initialValue: fillingTime,
              rules: [{
                required: false
              }]
            })(<RangePicker style={{width:'100%'}} />)}
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
