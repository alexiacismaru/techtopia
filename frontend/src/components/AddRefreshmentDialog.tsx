import { CreateRefreshmentStand } from '../model/./POI.ts'
import {z} from 'zod';
import {zodResolver} from "@hookform/resolvers/zod";
import {Controller, useForm} from "react-hook-form";
import {
    Box,
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    TextField,
} from '@mui/material'

interface RefreshmentStandDialogProps {
    isOpen: boolean;
    onSubmit: (attraction: CreateRefreshmentStand) => void;
    onClose: () => void;
}

const itemSchema: z.ZodType<CreateRefreshmentStand> = z.object({
    name: z.string().min(2, 'Name must be at least 2 characters'),
    isOpen: z.boolean(),
    image: z.string().url(),
});

export function AddRefreshmentDialog({isOpen, onSubmit, onClose}: RefreshmentStandDialogProps) {
    const {
        handleSubmit,
        control,
        formState: {errors},
    } = useForm<CreateRefreshmentStand>({
        resolver: zodResolver(itemSchema),
        defaultValues: {
            name: '',
            isOpen: false,
            image: '',
        },
    });

    return (
        <Dialog open={isOpen} onClose={onClose}>
            <form
                onSubmit={handleSubmit((data) => {
                    onSubmit(data)
                    onClose()
                })}
                style={{width: '400px', borderRadius: '20px'}}
            >
                <div style={{display: "flex", justifyContent: "space-between", alignItems: 'baseline'}}>
                    <DialogTitle>Add attraction</DialogTitle>
                    <Button sx={{padding: "10px", color: 'black'}} onClick={onClose}>
                        X
                    </Button>
                </div>
                <DialogContent>
                    <Box sx={{display: 'flex', flexDirection: 'column', gap: '0.5rem'}}>
                        <Controller
                            name="name"
                            control={control}
                            render={({field}) => (
                                <TextField
                                    {...field}
                                    label="Name"
                                    error={!!errors.name}
                                    helperText={errors.name?.message}
                                    required
                                />
                            )}
                        />
                        <Controller
                            name="isOpen"
                            control={control}
                            render={({field}) => (
                                <TextField
                                    {...field}
                                    label="isOpen"
                                    error={!!errors.isOpen}
                                    helperText={errors.isOpen?.message}
                                />
                            )}
                        />
                        <Controller
                            name="image"
                            control={control}
                            render={({field}) => (
                                <TextField
                                    {...field}
                                    label="Image"
                                    error={!!errors.image}
                                    helperText={errors.image?.message}
                                    required
                                />
                            )}
                        />
                    </Box>
                </DialogContent>
                <DialogActions style={{paddingRight: '1.5em', paddingBottom: '1.5em'}}>
                    <Button type="submit" variant="contained" sx={{backgroundColor: 'green'}}>
                        Add
                    </Button>
                </DialogActions>
            </form>
        </Dialog>
    )
}
