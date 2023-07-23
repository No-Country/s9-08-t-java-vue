import { http } from '@/http/httpInstance'

export const getCrewMembers = async () => {
  try {
    const res = await http.get('/crew-members/')
    return res.data
  } catch (error) {
    return []
  }
}
