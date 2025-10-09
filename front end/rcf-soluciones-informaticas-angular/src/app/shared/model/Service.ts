export default interface Service {
    id: number;
    code: string;
    name: string;
    description: string;
    basePriceCents: number;
    taxRate: number;
    durationMinutes: number;
    requiresOnSite: boolean;
    active: boolean;
    createdAt: string;
    updatedAt: string;
}
