import RolResponse from "./RolResponse";

export default interface User {
    id: number;
    email: string;
    password: string;
    fullName: string;
    role: RolResponse;
    phone: string;
    active: boolean;
}