import React, { PropTypes } from 'react'
import { Upload, Icon, message } from 'antd'
import styles from './UploadImage.less'

function getBase64(img, callback) {
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result));
  reader.readAsDataURL(img);
}

function beforeUpload(file) {
  const isJPG = file.type === 'image/jpeg';
  if (!isJPG) {
    message.error('You can only upload JPG file!');
  }
  const isLt2M = file.size / 1024 / 1024 < 2;
  if (!isLt2M) {
    message.error('Image must smaller than 2MB!');
  }
  return isJPG && isLt2M;
}

class UploadImage extends React.Component {
  state = {};
  handleChange = (info) => {
    if (info.file.status === 'done') {
      getBase64(info.file.originFileObj, imageUrl => this.setState({ imageUrl }));
      this.props.onChange && this.props.onChange(info.file.response.data);
    }
  }
  onRemove = () => {
    this.setState({ imageUrl: '' });
    this.props.onChange && this.props.onChange('');
  }
  render() {
    const imageUrl = this.state.imageUrl;
    return (
      <Upload
        className={styles.avatarUploader}
        name="file"
        showUploadList={true}
        action="/manager/files/uploadImage"
        beforeUpload={beforeUpload}
        onChange={this.handleChange}
        onRemove={() => this.onRemove()}
      >
        {
          imageUrl ?
            <img src={imageUrl} alt="" className={styles.avatar}/> :
            <Icon type="plus" className={styles.avatarUploaderTrigger} />
        }
      </Upload>
    )
  }
}

export default UploadImage
