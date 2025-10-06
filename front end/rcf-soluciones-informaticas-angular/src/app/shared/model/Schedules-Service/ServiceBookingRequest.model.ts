export default interface ServiceBookingRequest {
    orderItemId: number;
    userId: number;
    serviceId: number;
    technicianUserId: number;
    bookingStatusId: number;
    scheduledStart: string;
    scheduledEnd: string;
    addressSnapshot: string;
    notes: string;
}