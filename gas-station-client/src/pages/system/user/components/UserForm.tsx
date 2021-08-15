import React from "react";
import {ModalForm, ProFormText} from '@ant-design/pro-form';
import {UserInfo} from "@/pages/system/user/data";
import {saveUser, updateUser} from "@/pages/system/user/service";

interface UserFormProps {
  record?: UserInfo,
  onClose: (refresh: boolean) => void,
}

const UserForm: React.FC<UserFormProps> = ({record, onClose}) => {

  const handleOk = async (user: UserInfo) => {
    return (record ? updateUser(record.id, user) : saveUser(user)).then((res) => {
      if (res.code === 0) {
        onClose(true);
      }
      return res.code === 0;
    });
  }

  return (
    <ModalForm<UserInfo>
      initialValues={record}
      visible={true}
      title="用户编辑"
      width={400}
      modalProps={{
        onCancel: () => onClose(false)
      }}
      onFinish={handleOk}>
      <ProFormText name="username" label="账号" fieldProps={{maxLength: 16}} rules={[{
        required: true,
        message: '账号必填'
      }]}/>
      {
        !record && <ProFormText.Password name="password" label="密码" fieldProps={{maxLength: 16}} rules={[{
          required: true,
          message: '密码必填'
        }]}/>
      }
      <ProFormText name="name" label="姓名" fieldProps={{maxLength: 16}} rules={[{
        required: true,
        message: '姓名必填'
      }]}/>
      <ProFormText name="phone" label="手机号码" fieldProps={{maxLength: 11}} />
    </ModalForm>
  );
}

export default UserForm;
