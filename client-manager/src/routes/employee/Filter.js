import React, { PropTypes } from 'react'
import { Form, Button, Row, Col, Switch } from 'antd'
import { Search } from '../../components'

const Filter = ({
  location,
  onSearch,
  onAdd
}) => {
  const searchGroupProps = {
    field: 'name',
    keyword: location.query.name,
    size: 'large',
    select: true,
    selectOptions: [{ value: 'name', name: '名称' }],
    selectProps: {
      defaultValue: 'name'
    },
    onSearch: (value) => {
      onSearch(value)
    }
  }
  return (
    <Row gutter={24}>
      <Col lg={8} md={12} sm={16} xs={24} style={{ marginBottom: 16 }}>
        <Search {...searchGroupProps} />
      </Col>
      <Col lg={{ offset: 8, span: 8 }} md={12} sm={8} xs={24} style={{ marginBottom: 16, textAlign: 'right' }}>
        <Button size="large" type="ghost" onClick={onAdd}>添加</Button>
      </Col>
    </Row>
  )
}

Filter.propTypes = {
  form: PropTypes.object.isRequired,
  onSearch: PropTypes.func,
  onAdd: PropTypes.func,
  location: PropTypes.object
}

export default Form.create()(Filter)
