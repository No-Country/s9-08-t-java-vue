export interface IProfile {
  email: string
  username: string
  token: string
  roles: unknown[]
}

export interface IProfileAuth {
  profile: IProfile
}
