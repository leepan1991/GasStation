import React, { PropTypes } from 'react'
import { Transfer, Modal } from 'antd'

class RoleModal extends React.Component {
  static propTypes = {
    visible: PropTypes.bool,
    item: PropTypes.object,
    assignRoleList: PropTypes.array,
    onCancel: PropTypes.func,
    onOk: PropTypes.func
  };

  constructor (props) {
    super(props);
    let { assignRoleList } = props;
    let targetKeys = [];
    assignRoleList.forEach(function(role) {
      if (role.userId > 0) {
        targetKeys.push(role.id);
      }
    });
    this.state = {
      targetKeys
    };
  }

  handleOk() {
    let { item, onOk }  = this.props;
    let targetKeys = this.state.targetKeys;
    if (targetKeys) {
      let roles = [];
      targetKeys.forEach(function(roleId) {
        roles.push({
          id: roleId
        });
      });
      onOk({
        id: item.id,
        roles
      })
    }
  }

  handleChange(nextTargetKeys) {
    this.setState({ targetKeys: nextTargetKeys });
  }

  render() {
    let {
      visible,
      assignRoleList,
      onCancel
    } = this.props;

    let targetKeys = this.state.targetKeys;

    const modalOpts = {
      title: `分配角色`,
      width: 700,
      visible,
      onOk: this.handleOk.bind(this),
      onCancel,
      wrapClassName: 'vertical-center-modal'
    }

    const transferProps = {
      targetKeys,
      dataSource: assignRoleList,
      titles: ['未选', '已选'],
      operations: ['分配', '取消'],
      listStyle: {
        width: 300,
        height: 450
      },
      render: item => item.name,
      rowKey: record => record.id,
      onChange: this.handleChange.bind(this)
    }

    return (
      <Modal {...modalOpts}>
        <Transfer {...transferProps} />
      </Modal>
    )
  }
}

export default RoleModal
