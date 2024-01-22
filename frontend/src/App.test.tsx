import { render } from '@testing-library/react'
import App from './App'
import { test } from 'vitest'

test('renders without smoke', () => {
    render(<App />)
})
