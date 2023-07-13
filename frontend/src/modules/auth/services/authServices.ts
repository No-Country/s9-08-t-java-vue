import { http } from '@/http/httpInstance'
import { type ILoginResponse, type IRegisterResponse } from '../interfaces/Response'
import type { ILoginRequest, IRegisterRequest } from '../interfaces/Request'
import { type AxiosResponse } from 'axios'

export const authenticate = async (loginRequest: ILoginRequest): Promise<ILoginResponse> => {
  try {
    const response = await http.post<ILoginRequest, AxiosResponse<ILoginResponse>>(
      '/auth/authenticate',
      loginRequest
    )
    return response.data
  } catch (error) {
    throw 'Authentication error'
  }
}

export const register = async (registerRequest: IRegisterRequest): Promise<IRegisterResponse> => {
  try {
    return await http.post<IRegisterRequest, IRegisterResponse>('/auth/register', registerRequest)
  } catch (error) {
    throw 'Authentication error'
  }
}
