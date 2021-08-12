import React from "react";
import {ModalForm} from "@ant-design/pro-form";
import {Spin, Transfer} from "antd";
import {useUserGrantInfo, grantRole} from "../service";
import {UserInfo} from "@/pages/system/user/data";

interface GrantFormProps {
  userInfo: UserInfo,
  onClose: () => void
}

const GrantForm: React.FC<GrantFormProps> = ({userInfo, onClose}) => {

  const [roleList, targetKeys, setTargetKeys, loading] = useUserGrantInfo(userInfo.id);

  const list = roleList.map(role => {
    return {
      key: role.id + "",
      title: role.name,
      description: role.name,
    }
  });

  const handleChange = (nextTargetKeys: string[]) => {
    setTargetKeys(nextTargetKeys);
  }

  const handleFinish = () => {
    return grantRole(userInfo.id, targetKeys).then(res => {
      if (res.code === 0) {
        onClose();
      }
      return res.code === 0;
    });
  }

  return (
    <ModalForm title="分配角色"
               visible={true}
               width={450}
               onFinish={handleFinish}
               onVisibleChange={(value) => {
                 !value && onClose();
               }}>
      <Spin spinning={loading}>
        <Transfer<{ key: string, title: string }>
          titles={['未选', '已选']}
          dataSource={list}
          targetKeys={targetKeys}
          render={item => item.title}
          listStyle={{
            height: 300,
          }}
          onChange={handleChange}
        />
      </Spin>
    </ModalForm>
  );
}

export default GrantForm;
