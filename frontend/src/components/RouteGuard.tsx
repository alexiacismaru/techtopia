import { Alert } from '@mui/material'
import { ReactElement, useContext } from 'react'
import SecurityContext from '../context/SecurityContext.ts'

export interface RouteGuardProps {
    component: ReactElement
}

const RouteGuard = ({ component }: RouteGuardProps) => {
    const { isAuthenticated } = useContext(SecurityContext)

    if (isAuthenticated()) {
        return component
    } else {
        return <Alert severity="info">You are redirected to the login page</Alert>
    }
}

export default RouteGuard
