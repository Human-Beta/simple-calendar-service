export interface CalendarDay {
  date: Date,
  events: Event[]
}

export interface Event {
  id: number
  title: string
  description?: string
  startDate: Date
  endDate: Date
  location?: string
}