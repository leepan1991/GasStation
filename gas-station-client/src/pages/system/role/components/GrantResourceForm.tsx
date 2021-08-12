import React from "react";
import {RoleInfo} from "@/pages/system/role/data";
import {ModalForm} from "@ant-design/pro-form";
import {ResourceInfo} from "@/pages/system/resource/data";
import {grantResource, useRoleGrantInfo} from "@/pages/system/role/service";
import ProTable, {ProColumns} from "@ant-design/pro-table";
import {Table} from "antd";

interface GrantResourceFormProps {
  roleInfo: RoleInfo,
  onClose: () => void
}

const GrantResourceForm: React.FC<GrantResourceFormProps> = ({roleInfo, onClose}) => {

  const [resourceList, selectedKeys, setSelectedKeys, loading] = useRoleGrantInfo(roleInfo.id);

  const columns: ProColumns<ResourceInfo>[] = [{
    title: '名称',
    dataIndex: 'name',
  }, {
    title: '标识',
    dataIndex: 'code',
  }];

  const handleFinish = async () => {
    return grantResource(roleInfo.id, selectedKeys as string[]).then(res => {
      if (res.code === 0) {
        onClose();
      }
      return res.code === 0;
    });
  }

  const handleSelectChange = (selectedRowKeys: React.Key[]) => {
    setSelectedKeys(selectedRowKeys);
  }

  return (
    <ModalForm title="授权"
               visible={true}
               onFinish={handleFinish}
               onVisibleChange={(value) => !value && onClose()}>
      <ProTable<ResourceInfo>
        loading={loading}
        dataSource={resourceList}
        rowKey="id"
        columns={columns}
        pagination={false}
        search={false}
        params={{
          roleId: roleInfo.id
        }}
        rowSelection={{
          selectedRowKeys: selectedKeys,
          selections: [Table.SELECTION_ALL, Table.SELECTION_INVERT],
          onChange: handleSelectChange
        }}
      />
    </ModalForm>
  );
}

export default GrantResourceForm;
