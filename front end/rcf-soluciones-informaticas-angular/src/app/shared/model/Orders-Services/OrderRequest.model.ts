export default interface OrderRequest {
    userId: number;
    currencyCode: string;
    orderStatusId: number;
    paymentMethodCode: string;
    subtotalCents: number;
    taxTotalCents: number;
    totalCents: number;
    discountCents: number;
    notes: string;
}