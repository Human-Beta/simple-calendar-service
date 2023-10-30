export const formatDate = (date: Date | null) => {
  if (date) {
    const yyyy = date.getFullYear()
    const mm = (date.getMonth() + 1).toString().padStart(2, '0')
    const dd = date.getDate().toString().padStart(2, '0')
    const hh = date.getHours().toString().padStart(2, '0')
    const min = date.getMinutes().toString().padStart(2, '0')

    return `${yyyy}-${mm}-${dd}T${hh}:${min}`
  }
  return null
}
