export interface User {
    id: string;
    username: string;
    email: string;
    password: string;
}

export interface SignUp {
    id: string;
    username: string;
    email: string;
    password: string;
    confirmPassword: string;
}
export type CreateUser = Omit<SignUp, 'id'>;

export type Login = Omit<User, 'username' | 'id'>;
