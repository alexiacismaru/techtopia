import DOMPurify from 'dompurify'

export function SanitizedText({ input }: { input: string }) {
    return (
        <span
            dangerouslySetInnerHTML={{
                __html: DOMPurify.sanitize(input),
            }}
        />
    )
}
