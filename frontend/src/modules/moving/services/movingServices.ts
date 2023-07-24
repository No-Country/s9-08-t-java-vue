import { http } from '@/http/httpInstance'
import type { IMoving } from '../interfaces/IMoving'
import type { AxiosResponse } from 'axios'

export const getCrewMembers = async () => {
  try {
    const res = await http.get('/crew-members/')
    return res.data
  } catch (error) {
    return []
  }
}

export const saveMoving = async (data: IMoving) => {
  try {
    const res = await http.post<IMoving, AxiosResponse>('/moving')
    return res.data
  } catch (error) {
    throw 'Error'
  }
}
