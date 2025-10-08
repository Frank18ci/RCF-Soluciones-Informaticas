import DiscountType from "./DiscountType";

export default interface Discount {
    id: number;
    name: string;
    description: string;
    discountType: DiscountType;
    value: number;
    active: boolean;
    startDate: Date;
    endDate: Date;
}