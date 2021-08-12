import React from "react";
import {ModalForm, ProFormText} from "@ant-design/pro-form";
import {changePassword} from "@/pages/system/user/service";

interface PwdFormProps {
  userId: number,
  children: JSX.Element
}

interface ChangePasswordInfo {
  password: string,
  rePassword: string
}

const PwdForm: React.FC<PwdFormProps> = ({userId, children}) => {

  const handleOk = async (values: ChangePasswordInfo) => {
    return changePassword(userId, values.password).then(res => {
      return res.code === 0;
    });
  }

  return (
    <ModalForm<ChangePasswordInfo>
      title="修改密码"
      width={400}
      trigger={children}
      onFinish={handleOk}
    >
      <ProFormText.Password name="password" label="密码" fieldProps={{maxLength: 16}} rules={[{
        required: true,
        message: '密码必填'
      }]}/>
      <ProFormText.Password name="rePassword" label="确认密码" fieldProps={{maxLength: 16}} rules={[{
        required: true,
        message: '密码必填'
      }, ({getFieldValue}) => ({
        validator(rule, value) {
          if (!value || getFieldValue('password') === value) {
            return Promise.resolve();
          }
          return Promise.reject('两次密码不一致');
        },
      })]}/>
    </ModalForm>
  );
}

export default PwdForm;
