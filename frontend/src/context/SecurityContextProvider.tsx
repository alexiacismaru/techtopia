import { ReactNode, useEffect, useState } from 'react'
import SecurityContext from './SecurityContext'
import { addAccessTokenToAuthHeader, removeAccessTokenFromAuthHeader } from '../services/Auth'
import { isExpired } from 'react-jwt'
import Keycloak from 'keycloak-js'

interface IWithChildren {
    children: ReactNode
}

const keycloakConfig = {
    url: 'http://localhost:8180',
    realm: 'techtopiaRealm',
    clientId: 'techtopia-frontend',
}

const keycloak: Keycloak = new Keycloak(keycloakConfig)

export default function SecurityContextProvider({ children }: IWithChildren) {
    const [loggedInUser, setLoggedInUser] = useState<string | undefined>(undefined)

    useEffect(() => {
        keycloak.init({ onLoad: 'login-required' })
    }, [])

    keycloak.onAuthSuccess = () => {
        addAccessTokenToAuthHeader(keycloak.token)
        setLoggedInUser(keycloak.idTokenParsed?.name)
    }

    keycloak.onAuthLogout = () => {
        removeAccessTokenFromAuthHeader()
    }

    keycloak.onAuthError = () => {
        removeAccessTokenFromAuthHeader()
    }

    keycloak.onTokenExpired = () => {
        keycloak.updateToken(-1).then(function () {
            addAccessTokenToAuthHeader(keycloak.token)
            setLoggedInUser(keycloak.idTokenParsed?.name)
        })
    }

    function logout() {
        const logoutOptions = { redirectUri: import.meta.env.VITE_REACT_APP_URL }
        keycloak.logout(logoutOptions)
    }

    function isAuthenticated() {
        if (keycloak.token) return !isExpired(keycloak.token)
        else return false
    }

    return (
        <SecurityContext.Provider
            value={{
                isAuthenticated,
                loggedInUser,
                logout,
            }}
        >
            {children}
        </SecurityContext.Provider>
    )
}

