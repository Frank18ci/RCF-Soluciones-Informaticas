export default interface DiscountRequest {
    code: string;
    description: string;
    discountTypeId: number;
    value: number; 
    active: boolean;
    startDate: string; 
    endDate: string;  
}