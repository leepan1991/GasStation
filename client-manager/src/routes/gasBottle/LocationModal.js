import React, {PropTypes} from 'react'
import {Modal} from 'antd'
import {Map, Marker, NavigationControl, MarkerList, Polyline} from 'react-bmap'

const modal = ({
                 visible,
                 item = {},
                 locationList = [],
                 onOk,
                 onCancel
               }) => {
  function handleOk() {

  }

  const modalOpts = {
    title: `位置`,
    width: 900,
    visible,
    onOk: handleOk,
    onCancel,
    wrapClassName: 'vertical-center-modal'
  }

  return (
    <Modal {...modalOpts}>
      <Map style={{height: '800px'}}  center={{lng: item.longitude, lat: item.latitude}} zoom="15">
        {
          locationList.length > 0 ?
            <MarkerList
              data={locationList.map((item, index) => {
                return {text: index + 1, location:item.lng + ',' + item.lat}
              })}/>
            : <Marker position={{lng: item.longitude, lat: item.latitude}} icon="loc_blue"/>
        }
        <Polyline strokeColor='green'
                  path={locationList}/>
        <NavigationControl />
      </Map>
    </Modal>
  )
}

modal.propTypes = {
  visible: PropTypes.bool,
  item: PropTypes.object,
  locationList: PropTypes.array,
  onCancel: PropTypes.func,
  onOk: PropTypes.func
}

export default modal
