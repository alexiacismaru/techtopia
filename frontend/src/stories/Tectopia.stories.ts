import type { Meta, StoryObj } from "@storybook/react";
import Attraction from "../components/Attraction.tsx";

const meta = {
    title: 'Attraction',
    component: Attraction,
    parameters: {
        layout: 'centered',
    },
    tags: ['autodocs'],
    argTypes: {
        name: { control: 'text' },
        description: { control: 'text' },
        category: { control: 'text' },
        tags: { control: 'text' },
        waitingTime: { control: 'number' },
        isOpen: { control: 'boolean' },
        ageGroup: { control: 'text' },
    },
} satisfies Meta<typeof Attraction>

export default meta
type Story = StoryObj<typeof meta>

export const Primary: Story = {
    args: {
        id: 'test-attraction',
        name: 'Test Attraction',
        description: 'This is a test attraction',
        category: 'Test Category',
        tags: 'Test Tag',
        waitingTime: 100,
        isOpen: true,
        ageGroup: 'Test Age Group',
    },
}

export const Empty: Story = {
    args: {
        waitingTime: 0,
        isOpen: false,
    },
}
