import React from "react";
import {ModalForm, ProFormText,} from '@ant-design/pro-form';
import {saveEmployee, updateEmployee} from "@/pages/employee/service";
import {EmployeeInfo} from "@/pages/employee/data";

interface EmployeeFormProps {
  record?: EmployeeInfo,
  onClose: (refresh: boolean) => void,
}

const EmployeeForm: React.FC<EmployeeFormProps> = ({record, onClose}) => {

  const handleOk = async (item: EmployeeInfo) => {
    return (record ? updateEmployee(record.id, item) : saveEmployee(item)).then((res) => {
      if (res.code === 0) {
        onClose(true);
      }
      return res.code === 0;
    });
  }

  return (
    <ModalForm<EmployeeInfo>
      initialValues={record}
      visible={true}
      title="账户编辑"
      width={400}
      modalProps={{
        onCancel: () => onClose(false)
      }}
      onFinish={handleOk}>
      <ProFormText name="name" label="名称" fieldProps={{maxLength: 16}} rules={[{
        required: true,
        message: '名称必填'
      }]}/>
      <ProFormText name="phone" label="手机号码" fieldProps={{maxLength: 16}} rules={[{
        required: true,
        message: '手机号码必填'
      }]}/>
      <ProFormText.Password name="password" label="密码" fieldProps={{maxLength: 16}} rules={[{
        required: true,
        message: '密码必填'
      }]}/>
    </ModalForm>
  );
}

export default EmployeeForm;
