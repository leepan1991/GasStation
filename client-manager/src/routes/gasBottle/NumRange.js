import React, {PropTypes} from 'react'
import {Input, InputNumber} from 'antd'

class NumRange extends React.Component {
  static propTypes = {
    value: PropTypes.array,
    onChange: PropTypes.func
  }
  state = {
    value: this.props.value
  }

  minChange = (e) => {
    let {value = [undefined, undefined]} = this.state
    value = [e, value[1]]
    this.setState({
      value
    })
    this.props.onChange && this.props.onChange(value)
  }

  maxChange = (e) => {
    let {value = [undefined, undefined]} = this.state
    value = [value[0], e]
    this.setState({
      value
    })
    this.props.onChange && this.props.onChange(value)
  }

  render() {
    let {value} = this.state
    return (
      <Input.Group compact>
        <InputNumber style={{width: '47%',marginRight: 0,borderRightWidth:1}} onChange={this.minChange} defaultValue={value[0]} ref="minInput" min={1}/>
        <span style={{width: '6%', pointerEvents: 'none', textAlign: 'center'}} placeholder="-">-</span>
        <InputNumber style={{width: '47%',marginRight: 0}} onChange={this.maxChange} defaultValue={value[1]} ref="maxInput" min={1}/>
      </Input.Group>
    )
  }
}

export default NumRange
