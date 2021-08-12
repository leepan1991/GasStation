import React from "react";
import {ModalForm, ProFormText,} from '@ant-design/pro-form';
import {saveRole, updateRole} from "@/pages/system/role/service";
import {RoleInfo} from "@/pages/system/role/data";

interface RoleFormProps {
  record?: RoleInfo,
  onClose: (refresh: boolean) => void,
}

const RoleForm: React.FC<RoleFormProps> = ({record, onClose}) => {

  const handleOk = async (role: RoleInfo) => {
    return (record ? updateRole(record.id, role) : saveRole(role)).then((res) => {
      if (res.code === 0) {
        onClose(true);
      }
      return res.code === 0;
    });
  }

  return (
    <ModalForm<RoleInfo>
      initialValues={record}
      visible={true}
      title="角色编辑"
      width={400}
      modalProps={{
        onCancel: () => onClose(false)
      }}
      onFinish={handleOk}>
      <ProFormText name="name" label="名称" fieldProps={{maxLength: 16}} rules={[{
        required: true,
        message: '名称必填'
      }]}/>
    </ModalForm>
  );
}

export default RoleForm;
