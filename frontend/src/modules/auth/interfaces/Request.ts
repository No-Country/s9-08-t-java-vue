export interface ILoginRequest {
  email: string
  password: string
}

export interface IRegisterRequest extends ILoginRequest {
  username: string
}
