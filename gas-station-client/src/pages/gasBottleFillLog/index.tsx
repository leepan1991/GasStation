import React, {useState} from "react";
import {PageContainer} from "@ant-design/pro-layout";

const GasBottleFillLog: React.FC = () => {
  const [tabKey, setTabKey] = useState<string>('diwen');

  return (
    <PageContainer tabActiveKey={tabKey}
                   onTabChange={(k) => setTabKey(k)}
                   tabList={[{
                     tab: '低温绝热气瓶',
                     key: 'diwen'
                   }, {
                     tab: '压缩(永久)气体',
                     key: 'yasuo'
                   }, {
                     tab: '液体二氧化碳',
                     key: 'eryaghuatan'
                   }, {
                     tab: '混合气体',
                     key: 'hunhe'
                   }, {
                     tab: '溶解乙炔',
                     key: 'yique'
                   }]}>

    </PageContainer>
  );
}

export default GasBottleFillLog;
