import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import Attractions from './Attractions';

test('renders Attractions component', () => {
    render(<Attractions />);
    expect(screen.getByTestId('loader')).toBeInstanceOf(HTMLElement);
});

test('displays an error message if there is an error', () => {
    jest.mock('../hooks/usePOI.ts', () => ({
        useAttractions: () => ({
            isLoading: false,
            isError: true,
            attractions: [],
            addAttraction: jest.fn(),
        }),
    }));

    render(<Attractions />);
    expect(screen.getByText('Error')).toBeInstanceOf(HTMLElement);
});

test('displays attractions after loading', () => {
    jest.mock('../hooks/usePOI.ts', () => ({
        useAttractions: () => ({
            isLoading: false,
            isError: false,
            attractions: [
                {
                    id: '1',
                    name: 'attraction1',
                    description: 'description1',
                    image: 'image1',
                    tags: 'tags1',
                    ageGroup: 'ageGroup1',
                },
                {
                    id: '2',
                    name: 'attraction2',
                    description: 'description2',
                    image: 'image2',
                    tags: 'tags2',
                    ageGroup: 'ageGroup2',
                },
            ],
            addAttraction: jest.fn(),
        }),
    }));

    render(<Attractions />);
    expect(screen.getByTestId('attraction')).toBeInstanceOf(HTMLElement);
});

