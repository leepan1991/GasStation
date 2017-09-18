export function mediumFormat(value) {
  if (value === '0') {
    return '工业氧气'
  } else if (value === '1') {
    return '工业氮气'
  } else if (value === '2') {
    return '工业氩气'
  } else if (value === '3') {
    return '液体二氧化碳'
  } else if (value === '4') {
    return '液体氧'
  } else if (value === '5') {
    return '液体氮'
  } else if (value === '6') {
    return '液体氩'
  } else if (value === '7') {
    return '液化石油气'
  } else if (value === '8') {
    return '丙烷气'
  } else if (value === '9') {
    return '乙炔气'
  } else if (value === '10') {
    return '混合气'
  }
  return value
}
