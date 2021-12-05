import Moment from 'moment'  

export function formatTime(value){
  return Moment(value).format('YYYY-MM-DD HH:mm:ss')
}