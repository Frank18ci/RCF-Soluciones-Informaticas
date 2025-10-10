export default interface ServiceRequest {
    code: string;
    name: string;
    description: string;
    basePriceCents: number;
    taxRate: number;
    durationMinutes: number;
    requiresOnSite: boolean;
    active: boolean;
}