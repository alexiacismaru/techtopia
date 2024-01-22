export interface POI {
    id: string;
    name: string;
    description: string;
    tags: string;
    ageGroup: string;
    image: string;
}

export type CreatePOI = Omit<POI, 'id'>;

export interface RefreshmentStand {
    id: string;
    name: string;
    isOpen: boolean;
    image: string;
}

export type CreateRefreshmentStand = Omit<RefreshmentStand, 'id'>;
