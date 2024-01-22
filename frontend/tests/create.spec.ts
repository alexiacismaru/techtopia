import { expect, test } from '@playwright/test';
import { getAttraction } from '../src/services/API'

test('Test getAttractions', async ({ page }) => {
    page.route('**/attractions', async (route) => {
        const attractions = [
            { id: '1', name: 'Attraction 1' },
            { id: '2', name: 'Attraction 2' },
        ];
        await route.fulfill({
            status: 200,
            body: JSON.stringify(attractions),
        });
    });
    const result = await getAttraction('1');
    expect(result).toEqual([
        { id: '1', name: 'Attraction 1' },
        { id: '2', name: 'Attraction 2' },
    ]);
});
