import React from "react";
import {FooterToolbar, PageContainer} from "@ant-design/pro-layout";
import ProForm, {ProFormDatePicker, ProFormSelect, ProFormText,} from '@ant-design/pro-form';
import {Card} from "antd";
import {history} from 'umi';
import {queryById, updateById} from "@/pages/gasBottle/service";
import {GasBottleInfo} from "@/pages/gasBottle/data";
import {queryGasMediumList} from "@/pages/gasMedium/service";
import {GasMediumInfo} from "@/pages/gasMedium/data";

interface GasBottleFormProps {
  match: any
}

const GasBottleEdit: React.FC<GasBottleFormProps> = (props) => {
  const gasBottleId = props.match.params.id;
  return (
    <PageContainer>
      <Card>
        <ProForm<GasBottleInfo>
          submitter={{
            render: (_, dom) => <FooterToolbar>{dom}</FooterToolbar>,
          }}
          request={async () => {
            return queryById(gasBottleId).then((res) => {
              if (res.code == 0) {
                return res.data;
              }
              return {} as GasBottleInfo;
            })
          }}
          onFinish={async (values) => {
            return updateById(gasBottleId, values).then(res => {
              if (res.code == 0) {
                history.goBack();
                return true;
              }
              return false;
            });
          }}>
          <ProForm.Group align="center">
            <ProFormText width="sm" name="qpno" label="气瓶编号" rules={[{
              required: true, message: '必填'
            }]}/>
            <ProFormText width="sm" name="qppz" label="气瓶品种" rules={[{
              required: true, message: '必填'
            }]}/>
            <ProFormSelect width="sm" name="ccjz" label="充装介质" rules={[{
              required: true, message: '必填'
            }]} request={() => queryGasMediumList().then(res => {
              return res.data.map((medium: GasMediumInfo) => {
                return {label: medium.name, value: medium.code};
              })
            })}/>
            <ProFormText width="sm" name="qprj" label="充装容积" rules={[{
              required: true, message: '必填'
            }]}/>
            <ProFormText width="sm" name="gcyl" label="公称工作压力" rules={[{
              required: true, message: '必填'
            }]}/>
            <ProFormText width="sm" name="syyl" label="水压试验压力" rules={[{
              required: true, message: '必填'
            }]}/>
            <ProFormText width="sm" name="zzdw" label="制造单位" rules={[{
              required: true, message: '必填'
            }]}/>
            <ProFormText width="sm" name="ccrq" label="出厂日期" rules={[{
              required: true, message: '必填'
            }]}/>
            <ProFormText width="sm" name="yxqx" label="有效期限" rules={[{
              required: true, message: '必填'
            }]}/>
            <ProFormText width="sm" name="ccno" label="制造编号" rules={[{
              required: true, message: '必填'
            }]}/>
            <ProFormText width="sm" name="dwno" label="充装标志" rules={[{
              required: true, message: '必填'
            }]}/>
            <ProFormText width="sm" name="sydw" label="使用单位" rules={[{
              required: true, message: '必填'
            }]}/>
            <ProFormText width="sm" name="cqxz" label="产权性质" rules={[{
              required: true, message: '必填'
            }]}/>
            <ProFormDatePicker width="sm" name="jyrqf" label="上次检验日期" rules={[{
              required: true, message: '必填'
            }]}/>
            <ProFormDatePicker width="sm" name="jyrqn" label="下次检验日期" rules={[{
              required: true, message: '必填'
            }]}/>
            <ProFormText width="sm" name="qpmc" label="气瓶名称" rules={[{
              required: true, message: '必填'
            }]}/>
            <ProFormText width="sm" name="qpxh" label="气瓶型号" rules={[{
              required: true, message: '必填'
            }]}/>
            <ProFormText width="sm" name="qpStatus" label="状态" rules={[{
              required: true, message: '必填'
            }]}/>
            <ProFormText width="sm" name="jyno" label="检验报告编号"/>
            <ProFormText width="sm" name="jydw" label="检验单位"/>
            <ProFormText width="sm" name="syno" label="气瓶使用登记代码["/>
            <ProFormDatePicker width="sm" name="syrq" label="使用日期"/>
            <ProFormText width="sm" name="bgqk" label="变更情况"/>
            <ProFormText width="sm" name="syqk" label="停用情况"/>
            <ProFormText width="sm" name="bz" label="备注"/>
            <ProFormDatePicker width="sm" name="wriDate" label="建档日期"/>
            <ProFormText width="sm" name="sydjzbh" label="使用登记证编号"/>
            <ProFormDatePicker width="sm" name="fzrq" label="发证日期"/>
            <ProFormText width="sm" name="jgdm" label="机构代码"/>
            <ProFormText width="sm" name="impxh" label="导入的序号"/>
            <ProFormDatePicker width="sm" name="zjjyqr" label="最近检验日期"/>
            <ProFormText width="sm" name="qphd" label="气瓶厚度"/>
            <ProFormText width="sm" name="qpzl" label="气瓶重量"/>
            <ProFormText width="sm" name="czdw" label="充装单位"/>
            <ProFormText width="sm" name="ssqy" label="所属区域"/>
            <ProFormText width="sm" name="ssqyn" label="所属区域名字"/>
            <ProFormText width="sm" name="bffs" label="报废方式"/>
            <ProFormDatePicker width="sm" name="bfrq" label="报废日期"/>
            <ProFormText width="sm" name="bfczr" label="报废人"/>
          </ProForm.Group>
        </ProForm>
      </Card>
    </PageContainer>
  );
}

export default GasBottleEdit;
