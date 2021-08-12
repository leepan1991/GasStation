import React from "react";
import {ModalForm, ProFormText,} from '@ant-design/pro-form';
import {saveGasMedium, updateGasMedium} from "@/pages/gasMedium/service";
import {GasMediumInfo} from "@/pages/gasMedium/data";

interface GasMediumFormProps {
  record?: GasMediumInfo,
  onClose: (refresh: boolean) => void,
}

const GasMediumForm: React.FC<GasMediumFormProps> = ({record, onClose}) => {

  const handleOk = async (item: GasMediumInfo) => {
    return (record ? updateGasMedium(record.id, item) : saveGasMedium(item)).then((res) => {
      if (res.code === 0) {
        onClose(true);
      }
      return res.code === 0;
    });
  }

  return (
    <ModalForm<GasMediumInfo>
      initialValues={record}
      visible={true}
      title="气体介质编辑"
      width={400}
      modalProps={{
        onCancel: () => onClose(false)
      }}
      onFinish={handleOk}>
      <ProFormText name="code" label="标识" fieldProps={{maxLength: 16}} rules={[{
        required: true,
        message: '标识必填'
      }]}/>
      <ProFormText name="name" label="名称" fieldProps={{maxLength: 16}} rules={[{
        required: true,
        message: '名称必填'
      }]}/>
    </ModalForm>
  );
}

export default GasMediumForm;
