import { createContext } from 'react'

export interface ISecurityContext {
    isAuthenticated: () => boolean
    loggedInUser: string | undefined
    logout: () => void
}

export default createContext<ISecurityContext>({
    isAuthenticated: () => false,
    loggedInUser: undefined,
    logout: () => {},
})
