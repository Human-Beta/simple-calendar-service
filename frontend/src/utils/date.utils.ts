import type { Moment } from 'moment'
import moment from 'moment'

export const now = () => moment.utc()

export const copy = (date: Moment) => moment.utc(date)

export const parseDate = (value: string) => moment.utc(value)

export const formatDate = (date: Moment | null) => {
  if (date) {
    return date.format('yyyy-MM-DDTHH:mm')
  }
  return null
}
