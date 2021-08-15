import React from "react";
import {PageContainer} from "@ant-design/pro-layout";
import BraftEditor from 'braft-editor';
import 'braft-editor/dist/index.css';
import {Button, Card, Col, Form, Row} from "antd";
import {usePortalConfig, savePortalConfig} from "@/pages/system/portal/service";

const Portal: React.FC = () => {
  const [portalConfig, loading] = usePortalConfig(false);
  const [form] = Form.useForm();

  const handleSave = () => {
    form.validateFields().then(values => {
      savePortalConfig({
        qiye: values.qiye.toHTML(),
        anquan: values.anquan.toHTML(),
      }).then(res => {

      });
    });
  }

  return (
    <PageContainer loading={loading}
                   fixedHeader
                   extra={[
                     <Button key="save" type="primary" onClick={handleSave}>
                       保存
                     </Button>,
                   ]}>
      {
        portalConfig && <Form form={form} initialValues={{
          qiye: BraftEditor.createEditorState(portalConfig.qiye),
          anquan: BraftEditor.createEditorState(portalConfig.anquan),
        }}>
          <Row gutter={16}>
            <Col span={12}>
              <Card title="安全须知">
                <Form.Item name="anquan">
                  <BraftEditor excludeControls={['media']}/>
                </Form.Item>
              </Card>
            </Col>
            <Col span={12}>
              <Card title="企业信息">
                <Form.Item name="qiye">
                  <BraftEditor excludeControls={['media']}/>
                </Form.Item>
              </Card>
            </Col>
          </Row>
        </Form>
      }
    </PageContainer>
  );
}

export default Portal;
