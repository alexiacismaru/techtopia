import axios from 'axios'
import { POI, RefreshmentStand } from '../model/POI'

const BACKEND_URL = 'http://localhost:8093/api'

export const getAttractions = async () => {
    const url = BACKEND_URL + '/attractions'
    const response = await axios.get<POI[]>(url)
    return response.data
}

export const addAttraction = async (attractionData: Omit<POI, 'id'>) => {
    const url = BACKEND_URL + '/addAttraction'
    const response = await axios.post(url, attractionData)
    return response.data
}

export const getAttraction = async (attractionId: string) => {
    const url = BACKEND_URL + '/attractions'
    const response = await axios.get<POI>(`${url}/${attractionId}`)
    return response.data
}

export const getAttractionByTags = async (tags: string) => {
    const url = BACKEND_URL + '/attractions'
    const response = await axios.get<POI[]>(`${url}/tags/${tags}`)
    return response.data
}

export const getRefreshmentStands = async () => {
    const url = BACKEND_URL + '/refreshmentStands'
    const response = await axios.get<RefreshmentStand[]>(url)
    return response.data
}

export const getRefreshmentStand = async (refreshmentStandId: string) => {
    const url = BACKEND_URL + '/refreshmentStands'
    const response = await axios.get<RefreshmentStand>(`${url}/${refreshmentStandId}`)
    return response.data
}

export const addRefreshmentStand = async (refreshmentStandData: Omit<RefreshmentStand, 'id'>) => {
    const url = BACKEND_URL + '/addRefreshmentStand'
    const response = await axios.post(url, refreshmentStandData)
    return response.data
}

export const deleteAttraction = async (attractionId: string) => {
    const url = BACKEND_URL + '/attractions'
    const response = await axios.delete(`${url}/${attractionId}`)
    return response.data
}

export const deleteRefreshmentStand = async (refreshmentStandId: string) => {
    const url = BACKEND_URL + '/refreshmentStands'
    const response = await axios.delete<RefreshmentStand>(`${url}/${refreshmentStandId}`)
    return response.data
}
