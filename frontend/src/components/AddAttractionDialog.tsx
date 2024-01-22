import {CreatePOI} from "../model/./POI.ts";
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

interface AttractionDialogProps {
    isOpen: boolean;
    onSubmit: (attraction: CreatePOI) => void;
    onClose: () => void;
}

const itemSchema: z.ZodType<CreatePOI> = z.object({
    name: z.string().min(2, 'Name must be at least 2 characters'),
    description: z.string(),
    tags: z.string(),
    ageGroup: z.string(),
    image: z.string().url(),
})

export function AddAttractionDialog({isOpen, onSubmit, onClose}: AttractionDialogProps) {
    const {
        handleSubmit,
        control,
        formState: {errors},
    } = useForm<CreatePOI>({
        resolver: zodResolver(itemSchema),
        defaultValues: {
            name: '',
            description: '',
            tags: '',
            ageGroup: '',
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
                            name="description"
                            control={control}
                            render={({field}) => (
                                <TextField
                                    {...field}
                                    label="Description"
                                    error={!!errors.description}
                                    helperText={errors.description?.message}
                                />
                            )}
                        />
                        <Controller
                            name="tags"
                            control={control}
                            render={({field}) => (
                                <TextField
                                    {...field}
                                    label="Tags"
                                    error={!!errors.tags}
                                    helperText={errors.tags?.message}
                                    required
                                />
                            )}
                        />
                        <Controller
                            name="ageGroup"
                            control={control}
                            render={({field}) => (
                                <TextField
                                    {...field}
                                    label="Age group"
                                    error={!!errors.ageGroup}
                                    helperText={errors.ageGroup?.message}
                                    required
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
