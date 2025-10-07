export default interface DiscountRequest {
    code: string;
    description: string;
    discountTypeId: number;
    value: number; 
    startDate: string; 
    endDate: string;  
}