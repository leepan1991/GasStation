import React, {useState} from "react";
import {Popconfirm, Spin} from "antd";
import {PopconfirmProps} from "antd/lib/popconfirm";
import {Result} from "@/utils/request";
import styles from './style.less';

interface FetchPopconfirmProps extends PopconfirmProps {
  params: any,
  request: (p: any) => Promise<Result<any>>,
}

const Index: React.FC<FetchPopconfirmProps> = ({children, params, request, onConfirm, ...reset}) => {

  const [loading, setLoading] = useState<boolean>(false);

  const handleConfirm = (event: React.MouseEvent<HTMLElement> | undefined) => {
    setLoading(true);
    request(params).then(res => {
      if (res.code === 0) {
        if (onConfirm) {
          onConfirm(event);
        }
      }
    }).finally(() => {
      setLoading(false);
    })
  }

  return (
    <Popconfirm {...reset} onConfirm={handleConfirm}>
      <span className={styles.loading}><Spin spinning={loading}>{children}</Spin></span>
    </Popconfirm>
  );
}

export default Index;
